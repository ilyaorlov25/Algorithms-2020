@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson6

import java.io.File

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Если на входе граф с циклами, бросить IllegalArgumentException
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    TODO()
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
fun Graph.longestSimplePath(): Path {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
 * поэтому задача присутствует в этом разделе
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val answer = mutableSetOf<String>()
    val lines = File(inputName).readLines().map { it.split(" ") }

    for (word in words) {
        val wordLength = word.length
        found@ for (lineIndex in lines.indices) {
            val line = lines[lineIndex]
            for (letterIndex in line.indices) {
                val letter = line[letterIndex]
                if (letter == word[0].toString()) {
                    if (searchWord(lines, lineIndex, letterIndex, word, 0, wordLength)) {
                        answer.add(word)
                        break@found
                    }
                }
            }
        }
    }

    return answer
}

fun searchWord(
    lines: List<List<String>>,
    lineIndex: Int,
    letterIndex: Int,
    word: String,
    startIndex: Int,
    wordLength: Int
): Boolean {
    if (startIndex == wordLength - 1) return true

    if (checkLeft(lines, lineIndex, letterIndex, word[startIndex + 1])) {
        if (searchWord(lines, lineIndex, letterIndex - 1, word, startIndex + 1, wordLength)) return true
    }
    if (checkRight(lines, lineIndex, letterIndex, word[startIndex + 1])) {
        if (searchWord(lines, lineIndex, letterIndex + 1, word, startIndex + 1, wordLength)) return true
    }
    if (checkUp(lines, lineIndex, letterIndex, word[startIndex + 1])) {
        if (searchWord(lines, lineIndex - 1, letterIndex, word, startIndex + 1, wordLength)) return true
    }
    if (checkDown(lines, lineIndex, letterIndex, word[startIndex + 1])) {
        if (searchWord(lines, lineIndex + 1, letterIndex, word, startIndex + 1, wordLength)) return true
    }

    return false
}

fun checkLeft(lines: List<List<String>>, lineIndex: Int, letterIndex: Int, letterSuspectLeft: Char): Boolean {
    if (letterIndex == 0) return false
    return lines[lineIndex][letterIndex - 1] == letterSuspectLeft.toString()
}

fun checkRight(lines: List<List<String>>, lineIndex: Int, letterIndex: Int, letterSuspectRight: Char): Boolean {
    if (letterIndex == lines[lineIndex].size - 1) return false
    return lines[lineIndex][letterIndex + 1] == letterSuspectRight.toString()
}

fun checkUp(lines: List<List<String>>, lineIndex: Int, letterIndex: Int, letterSuspectUp: Char): Boolean {
    if (lineIndex == 0) return false
    return lines[lineIndex - 1][letterIndex] == letterSuspectUp.toString()
}

fun checkDown(lines: List<List<String>>, lineIndex: Int, letterIndex: Int, letterSuspectDown: Char): Boolean {
    if (lineIndex == lines.size - 1) return false
    return lines[lineIndex + 1][letterIndex] == letterSuspectDown.toString()
}