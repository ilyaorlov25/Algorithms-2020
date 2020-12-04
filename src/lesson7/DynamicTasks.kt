@file:Suppress("UNUSED_PARAMETER")

package lesson7

import kotlin.math.max

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    // Трудоёмкость: O(N * M)
    // Ресурсоёмкость: O((N + 1)*(M + 1)) = O(N * M), N = first.length, M = second.length

    val firstLength = first.length
    val secondLength = second.length
    val subseq = Array(firstLength + 1) { IntArray(secondLength + 1) { 0 } }

    for (i in 1..firstLength) {
        for (j in 1..secondLength) {
            if (first[i - 1] == second[j - 1])
                subseq[i][j] = 1 + subseq[i - 1][j - 1]
            else
                subseq[i][j] = max(subseq[i - 1][j], subseq[i][j - 1])
        }
    }

    val answer = StringBuilder()
    var i = firstLength
    var j = secondLength

    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                answer.insert(0, first[i - 1])
                i--
                j--
            }
            subseq[i - 1][j] == subseq[i][j] -> {
                i--
            }
            else -> {
                j--
            }
        }
    }

    return answer.toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    // Трудоёмкость: O(N^2)
    // Ресурсоёмкость: O(N)

    val size = list.size
    if (size <= 1) return list
    val subseq = IntArray(size) { 1 }
    val link = IntArray(size) { -1 }
    var maxLength = 1
    var indexOfLongest = 0

    for (i in 1 until size) {
        for (j in 0 until i) {
            if (list[i] > list[j] && subseq[i] <= subseq[j]) {
                subseq[i] = subseq[j] + 1
                link[i] = j
            }
        }
        if (subseq[i] > maxLength) {
            indexOfLongest = i
            maxLength = subseq[i]
        }
    }

    val result = mutableListOf<Int>()

    while (indexOfLongest >= 0) {
        result.add(0, list[indexOfLongest])
        indexOfLongest = link[indexOfLongest]
    }

    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5