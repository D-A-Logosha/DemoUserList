package com.example.demouserlist.data

import androidx.annotation.DrawableRes
import com.example.demouserlist.R
import kotlin.random.Random

typealias UsersListener = (users: List<User>) -> Unit

class UsersRepository {

    private var users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        users = (1..100).map {
            val firstName: String
            val lastName: String

            @DrawableRes
            val image: Int

            if (Random.nextBoolean()) {
                firstName = maleFirstName[Random.nextInt(0, maleFirstName.lastIndex)]
                lastName = maleLastName[Random.nextInt(0, maleFirstName.lastIndex)]
                image = maleAvatars[Random.nextInt(0, maleAvatars.lastIndex)]

            } else {
                firstName = femaleFirstName[Random.nextInt(0, maleFirstName.lastIndex)]
                lastName = femaleLastName[Random.nextInt(0, maleFirstName.lastIndex)]
                image = femaleAvatars[Random.nextInt(0, femaleAvatars.lastIndex)]
            }

            User(
                id = it,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = randomPhoneNumber(),
                image = image
            )
        }.toMutableList()
    }

    fun get(): List<User> {
        return users
    }

    fun editUser(user: User) {
        val indexToEdit = users.indexOfFirst { it.id == user.id }
        if (indexToEdit != -1) {
            users[indexToEdit] = user
            notifyChanges()
        }
    }

    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }
}

private fun randomPhoneNumber() =
    "+7" + " (${Random.nextInt(300, 999)}) " +
            "%03d".format(Random.nextInt(999)) + "-" +
            "%02d".format(Random.nextInt(99)) + "-" +
            "%02d".format(Random.nextInt(99))

private val maleFirstName = listOf(
    "Максим", "Михаил", "Александр", "Дмитрий", "Денис", "Илья", "Андрей", "Даниил", "Артём",
    "Иван", "Алексей", "Никита", "Павел", "Евгений", "Антон", "Лев", "Эльдар", "Григорий",
    "Владимир", "Руслан", "Василий", "Виталий", "Вячеслав", "Игнат", "Николай", "Олег", "Ренат",
    "Роман", "Сергей", "Тимур", "Богдан", "Гарик", "Давид", "Камиль", "Кирилл", "Константин",
    "Леонид", "Матвей", "Степан", "Филипп", "Аркадий", "Вадим", "Виктор", "Георгий", "Егор",
    "Макар", "Семён", "Станислав", "Тимофей", "Юрий", "Всеволод"
)
private val maleLastName = listOf(
    "Агеев", "Аксенов", "Александров", "Александров", "Андреев", "Андреев", "Андреев", "Афанасьев",
    "Басов", "Белов", "Белов", "Беляев", "Бобров", "Васильев", "Волков", "Волков", "Волков",
    "Волков", "Голиков", "Горбунов", "Давыдов", "Демьянов", "Дмитриев", "Дубровин", "Дьяконов",
    "Елисеев", "Ермолов", "Ефремов", "Жуков", "Зайцев", "Захаров", "Зиновьев", "Зиновьев",
    "Золотарев", "Иванов", "Калинин", "Калинин", "Карасев", "Карасев", "Карпов", "Карпов",
    "Касаткин", "Климов", "Климов", "Климов", "Коновалов", "Кононов", "Корчагин", "Кузнецов",
    "Кулагин", "Кулешов", "Куликов", "Лавров", "Лазарев", "Ларин", "Лебедев", "Лебедев", "Лебедев",
    "Логинов", "Максимов", "Медведев", "Медведев", "Михайлов", "Михайлов", "Молчанов", "Никитин",
    "Николаев", "Орлов", "Орлов", "Осипов", "Осипов", "Павлов", "Петров", "Петухов", "Пименов",
    "Пирогов", "Попов", "Попов", "Попов", "Романов", "Рыжов", "Свиридов", "Седов", "Семенов",
    "Сергеев", "Ситников", "Смирнов", "Соболев", "Сорокин", "Степанов", "Суворов", "Терехов",
    "Терехов", "Титов", "Трофимов", "Федотов", "Филатов", "Черкасов", "Черняев", "Яковлев"
)
private val femaleFirstName = listOf(
    "Анна", "Мария", "Юлия", "Алёна", "Анастасия", "Екатерина", "Дарья", "Ксения", "Кристина",
    "Алиса", "Яна", "Ольга", "Александра", "Светлана", "Елизавета", "Маргарита", "Елена", "Агата",
    "Юлиана", "Ирина", "Алина", "Арина", "Валерия", "Виктория", "Диана", "Ева", "Карина",
    "Каролина", "Марина", "Наталья", "Варвара", "Василиса", "Вера", "Любовь", "Марьяна", "Надежда",
    "Оксана", "Регина", "Софья", "Татьяна", "Алла", "Ангелина", "Вероника", "Евгения", "Жанна",
    "Лилия", "Милана", "Полина", "Рената", "Эльвира"
)
private val femaleLastName = listOf(
    "Агафонова", "Ананьева", "Андреева", "Архипова", "Архипова", "Афанасьева", "Белякова",
    "Блохина", "Богданова", "Бочарова", "Васильева", "Виноградова", "Владимирова", "Владимирова",
    "Волошина", "Воронова", "Герасимова", "Гончарова", "Гордеева", "Горшкова", "Грачева",
    "Григорьева", "Григорьева", "Денисова", "Денисова", "Дмитриева", "Дорохова", "Егорова",
    "Емельянова", "Ермолова", "Захарова", "Зиновьева", "Зиновьева", "Иванова", "Игнатьева",
    "Калинина", "Карпова", "Касаткина", "Киселева", "Колесникова", "Колпакова", "Комиссарова",
    "Корнилова", "Королева", "Короткова", "Короткова", "Крылова", "Кузнецова", "Кузнецова",
    "Кузнецова", "Кузнецова", "Кузнецова", "Кузьмина", "Лаптева", "Ларина", "Лебедева",
    "Леонтьева", "Логинова", "Лукина", "Львова", "Макарова", "Макарова", "Минина", "Михайлова",
    "Михайлова", "Морозова", "Некрасова", "Никитина", "Никитина", "Никитина", "Никифорова",
    "Никифорова", "Николаева", "Овчинникова", "Орлова", "Пантелеева", "Попова", "Попова", "Попова",
    "Романова", "Руднева", "Савельева", "Семенова", "Сергеева", "Сидорова", "Смирнова", "Соболева",
    "Сорокина", "Субботина", "Суворова", "Титова", "Тихонова", "Федорова", "Филиппова", "Филиппова",
    "Хомякова", "Хохлова", "Чеботарева", "Шувалова", "Яковлева"
)

private val maleAvatars = listOf(
    R.drawable.male_1, R.drawable.male_2, R.drawable.male_3, R.drawable.male_4,
    R.drawable.male_5, R.drawable.male_6, R.drawable.male_7, R.drawable.male_8,
    R.drawable.male_9, R.drawable.male_10, R.drawable.male_11, R.drawable.male_12,
    R.drawable.male_13, R.drawable.male_14, R.drawable.male_15, R.drawable.male_16,
    R.drawable.male_17, R.drawable.male_18, R.drawable.male_19, R.drawable.male_20,
    R.drawable.male_21, R.drawable.male_22, R.drawable.male_23, R.drawable.male_24,
    R.drawable.male_25, R.drawable.male_26, R.drawable.male_27, R.drawable.male_28,
    R.drawable.male_29, R.drawable.male_30, R.drawable.male_31, R.drawable.male_32,
    R.drawable.male_33, R.drawable.male_34, R.drawable.male_35, R.drawable.male_36,
    R.drawable.male_37, R.drawable.male_38, R.drawable.male_39, R.drawable.male_40,
    R.drawable.male_41, R.drawable.male_42, R.drawable.male_43, R.drawable.male_44,
    R.drawable.male_45, R.drawable.male_46, R.drawable.male_47, R.drawable.male_48
)

private val femaleAvatars = listOf(
    R.drawable.female_1, R.drawable.female_2, R.drawable.female_3, R.drawable.female_4,
    R.drawable.female_5, R.drawable.female_6, R.drawable.female_7, R.drawable.female_8,
    R.drawable.female_9, R.drawable.female_10, R.drawable.female_11, R.drawable.female_12,
    R.drawable.female_13, R.drawable.female_14, R.drawable.female_15, R.drawable.female_16,
    R.drawable.female_17, R.drawable.female_18, R.drawable.female_19, R.drawable.female_20,
    R.drawable.female_21, R.drawable.female_22, R.drawable.female_23, R.drawable.female_24,
    R.drawable.female_25, R.drawable.female_26, R.drawable.female_27, R.drawable.female_28,
    R.drawable.female_29, R.drawable.female_30, R.drawable.female_31, R.drawable.female_32,
    R.drawable.female_33, R.drawable.female_34, R.drawable.female_35, R.drawable.female_36,
    R.drawable.female_37, R.drawable.female_38, R.drawable.female_39, R.drawable.female_40,
    R.drawable.female_41, R.drawable.female_42, R.drawable.female_43, R.drawable.female_44,
    R.drawable.female_45, R.drawable.female_46, R.drawable.female_47, R.drawable.female_48
)