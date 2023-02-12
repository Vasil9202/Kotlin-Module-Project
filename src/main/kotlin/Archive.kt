data class Archive(val archiveName: String) {
    companion object {
        val archiveList = arrayListOf<Archive>()
    }

    val notesList = arrayListOf<Note>()

    constructor() : this("")

    init {
        if (!this.archiveName.isEmpty())
            archiveList.add(this)
    }

    enum class ArchiveEnum(val menu: String) {
        CREATE("0. Создать архив"), CHOOSE("1. Открыть архив"), EXIT("2. Выход")
    }

    fun menuArchive() {
        println("Меню архивов")
        for (command in Archive.ArchiveEnum.values())
            println(command.menu)
        val command = Util.inputDigitChoice()
        when (command) {
            0 -> {
                createArchive()
                menuArchive()
            }

            1 -> openArchive()
            2 -> return
            -1 -> {
                println(WrongInputError.WRONG_FORMAT.error)
                menuArchive()
            }

            else -> {
                println(WrongInputError.WRONG_NUMBER.error)
                menuArchive()
            }
        }
    }

    fun createArchive() {
        println("Создание архива")
        println("Введите имя создаваемого архива")
        val name = Util.inputText()
        println("Вы уверены, что хотите создать архив с именем: \"$name\" \n 0. Да \n 1. Нет ")
        val answer = Util.inputDigitChoice()
        when (answer) {
            0 -> {
                Archive(name)
                println("Архив $name создан успешно!")
            }

            1 -> createArchive()
            -1 -> {
                println(WrongInputError.WRONG_FORMAT.error)
                createArchive()
            }

            else -> {
                println(WrongInputError.WRONG_NUMBER.error)
                createArchive()
            }
        }
    }

    fun openArchive() {
        if (archiveList.isEmpty()) {
            println("Список архивов пуст \n")
            menuArchive()
            return
        }
        println("Список архивов")
        println("Выберите один из архивов")
        for ((index, value) in archiveList.withIndex()) {
            println("$index. ${value.archiveName}")
        }
        println("${archiveList.size}. Выход")

        val command = Util.inputDigitChoice()
        if (command == archiveList.size) {
            menuArchive()
        } else if (command == -1) {
            println(WrongInputError.WRONG_FORMAT.error)
            openArchive()
        } else if (command > archiveList.size) {
            println(WrongInputError.WRONG_NUMBER.error)
            openArchive()
        } else {
            archiveList[command].noteMenu()
        }
    }

    fun noteMenu() {
        println("Меню заметок")
        for (commandNote in Note.NoteEnum.values())
            println(commandNote.menu)
        val command = Util.inputDigitChoice()
        when (command) {
            0 -> {
                createNote()
                noteMenu()
            }

            1 -> openNote()
            2 -> {
                openArchive()
            }

            -1 -> {
                println(WrongInputError.WRONG_FORMAT.error)
                noteMenu()
            }

            else -> {
                println(WrongInputError.WRONG_NUMBER.error)
                noteMenu()
            }
        }
    }

    fun createNote() {
        println("Создание заметки")
        println("Введите имя создаваемой заметки")
        val noteName = Util.inputText()
        println("Имя заметки: \"$noteName\". Введите содержание заметки.")
        val noteBody = Util.inputText()
        if (!noteBody.isEmpty() && !noteName.isEmpty()) {
            notesList.add(Note(noteName, noteBody))
            println("Заметка \"$noteName\" сохранена")
        }
    }

    fun openNote() {
        if (notesList.isEmpty()) {
            println("Список заметок пуст \n")
            noteMenu()
            return
        }
        println("Список заметок")
        println("Выберите одну из заметок")
        for ((index, note) in this.notesList.withIndex()) {
            println("$index. ${note.noteName}")
        }
        println("${notesList.size}. Выход")
        val command = Util.inputDigitChoice()
        if (command == notesList.size) {
            noteMenu()
            return
        } else if (command > notesList.size) {
            println(WrongInputError.WRONG_NUMBER.error)
            openNote()
            return
        } else if (command == -1) {
            println(WrongInputError.WRONG_FORMAT.error)
            openNote()
            return
        } else {
            println("${notesList[command].noteName}\n ${notesList[command].noteText} \n \\\\Для возврата в меню введите нажмите клавишу Enter\\\\")
            val exit = Util.inputText()
            openNote()
        }
    }


}