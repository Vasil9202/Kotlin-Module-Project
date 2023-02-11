enum class WrongInputError(val error: String) {
    WRONG_NUMBER("Такого пункта нет, необходимо ввести одну цифру из нужного пункта"),
    WRONG_FORMAT("Необходимо ввести одно число, попробуйте еще раз.")
}