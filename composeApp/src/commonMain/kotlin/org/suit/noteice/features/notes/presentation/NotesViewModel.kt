package org.suit.noteice.features.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.could_not_delete_notes
import noteice.composeapp.generated.resources.could_not_save_note
import org.suit.noteice.features.notes.Note
import org.suit.noteice.features.notes.data.NoteTimeFormatter
import org.suit.noteice.features.notes.domain.NotesRepository
import org.suit.noteice.features.notes.presentation.ui.NoteInputData
import org.suit.noteice.utils.CustomResult
import org.suit.noteice.utils.ktor.UnauthorizedException
import org.suit.noteice.utils.ui.NotesUIEvent
import org.suit.noteice.utils.ui.UIText

class NotesViewModel(
    private val notesRepository: NotesRepository,
    private val noteTimeFormatter: NoteTimeFormatter
): ViewModel() {
    private val _uiState = MutableStateFlow(NotesUIState())
    val uiState = _uiState
        .onStart {
            fetchNotes(refresh = false)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesUIState())

    private val _uiEvent = MutableSharedFlow<NotesUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun handleIntent(intent: NotesIntent) {
        when (intent) {
            is NotesIntent.RefetchNotes -> fetchNotes(refresh = true)
            is NotesIntent.OpenNote -> openNote(intent)
            is NotesIntent.SelectNote -> selectNote(intent)
            is NotesIntent.SelectAllNotes -> selectAllNotes(intent)
            is NotesIntent.PerformNoteInput -> performNoteInput(intent.noteInputData)
            is NotesIntent.SaveOpenedNote -> saveOpenedNote()
            is NotesIntent.DeleteSelectedNotes -> deleteSelectedNotes()
        }
    }

    private fun deleteSelectedNotes() {
        viewModelScope.launch {
            try {
                updateNotesDeletionResult(CustomResult.InProgress)
                val selectedNotes = _uiState.value.selectedNotes
                notesRepository.deleteNotes(selectedNotes)
                _uiState.update { it.copy(
                    notes = it.notes.filterNot { selectedNotes.contains(it) },
                    selectedNotes = emptyList()
                ) }
                updateNotesDeletionResult(CustomResult.Success)
            } catch (e: Exception) {
                println(e)
                _uiEvent.emit(NotesUIEvent.ShowSnackbar(UIText.StringResource(Res.string.could_not_delete_notes)))
                updateNotesDeletionResult(CustomResult.Error)
            }
        }
    }

    private fun saveOpenedNote() {
        viewModelScope.launch {
            try {
                val currNote = _uiState.value.openedNote

                if (currNote == null || currNote.let { it.title.isBlank() || it.content.isBlank() }
                    || _uiState.value.notes.find { it.id == currNote.id && (it.title == currNote.title
                            && it.content == currNote.content)} != null ) return@launch

                val savedNote: Note
                if (currNote.id == 0L) {
                    savedNote = notesRepository.saveNote(currNote)
                } else {
                    savedNote = notesRepository.editNote(currNote)
                }
                println(savedNote)
                _uiState.update { it.copy(notes =
                (if (!it.notes.map { it.id }.contains(savedNote.id)) it.notes + savedNote
                else it.notes.map { if (it.id == savedNote.id) savedNote else it })
                    .sortedByDescending { it.lastModifiedAt?.epochSecond })}

            } catch (e: Exception) {
                println(e)
                _uiEvent.emit(NotesUIEvent.ShowSnackbar(UIText.StringResource(Res.string.could_not_save_note)))
            }
        }
    }

    private fun fetchNotes(refresh: Boolean) {
        val updateResult = {result: CustomResult ->if (refresh) updateNotesRefreshResult(result)
        else updateNotesFetchResult(result)}
        viewModelScope.launch {
            updateResult(CustomResult.InProgress)
            try {
                val notes = notesRepository.fetchNotes()
                if (notes == null) {
                    _uiEvent.emit(NotesUIEvent.NavigateToAuth)
                    updateResult(CustomResult.None)
                    return@launch
                }
                _uiState.update { it.copy(notes) }
                updateResult(CustomResult.Success)
            } catch (e: Exception) {
                println(e)
                if (e is UnauthorizedException) {
                    _uiEvent.emit(NotesUIEvent.NavigateToAuth)
                    updateResult(CustomResult.None)
                    return@launch
                }
                updateResult(CustomResult.Error)
            }
        }
    }

    private fun performNoteInput(noteInputData: NoteInputData) {
        val selectedNote = _uiState.value.openedNote ?: return
        _uiState.update { it.copy(
            openedNote = selectedNote.let {
                when (noteInputData) {
                    is NoteInputData.Title -> it.copy(title = noteInputData.title.take(80))
                    is NoteInputData.Content -> it.copy(content = noteInputData.content.take(1000))
                }
            }
        ) }
    }

    private fun openNote(intent: NotesIntent.OpenNote) {
        viewModelScope.launch {
            val foundNote = findNoteById(intent.id)
            // if id is 0, open a fresh new note
            _uiState.update { it.copy(
                openedNote = if (intent.id == 0L) Note() else foundNote,
                openedNoteLastModifiedTime = if (foundNote != null) noteTimeFormatter.format(foundNote.lastModifiedAt!!) else null
            ) }
        }
    }
    private fun selectNote(intent: NotesIntent.SelectNote) {
        val foundNote = findNoteById(intent.id) ?: return
        _uiState.update {
            it.copy(selectedNotes =
            if (!it.selectedNotes.contains(foundNote)) it.selectedNotes + foundNote
            else it.selectedNotes - foundNote)
        }
    }
    private fun selectAllNotes(intent: NotesIntent.SelectAllNotes) = _uiState.update {
        it.copy(selectedNotes = if (intent.select) it.notes else emptyList())
    }

    private fun findNoteById(id: Long?): Note? = _uiState.value.notes.firstOrNull { it.id == id }

    private fun updateNotesDeletionResult(result: CustomResult) =
        _uiState.update { it.copy(notesDeletionResult = result) }
    private fun updateNotesFetchResult(result: CustomResult) =
        _uiState.update { it.copy(notesFetchResult = result) }
    private fun updateNotesRefreshResult(result: CustomResult) =
        _uiState.update { it.copy(notesRefreshResult = result) }
}

data class NotesUIState(
    val notes: List<Note> = emptyList(),
    val openedNote: Note? = null,
    val openedNoteLastModifiedTime: String? = null,
    val selectedNotes: List<Note> = emptyList(),
    val notesDeletionResult: CustomResult = CustomResult.None,
    val notesFetchResult: CustomResult = CustomResult.None,
    val notesRefreshResult: CustomResult = CustomResult.None
)