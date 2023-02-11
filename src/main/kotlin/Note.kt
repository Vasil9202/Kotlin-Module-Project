data class Note(val noteName: String, val noteText: String){
    enum class NoteEnum(val menu: String) {
        CREATE("0. Создать заметку"), CHOOSE("1. Открыть заметку"), EXIT("2. Выход")
    }
}
