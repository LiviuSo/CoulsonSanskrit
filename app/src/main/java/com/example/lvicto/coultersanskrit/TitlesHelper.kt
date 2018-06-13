package com.example.lvicto.coultersanskrit

import kotlin.collections.ArrayList

/**
 * Returns the titles/headers of the chapters/sections
 */
class TitlesHelper(private var titles: ArrayList<String>) { // todo: write unit tests

    //    private var titles: ArrayList<String> = arrayListOf("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4") // initially contains only chapter titles
    companion object {
        private var sectionTitles = mapOf(
                0 to arrayListOf("Section 11", "Section 12", "Section 13", "Section 14")
                , 1 to arrayListOf("Section 21", "Section 22", "Section 23", "Section 24")
                , 2 to arrayListOf("Section 31", "Section 32", "Section 33", "Section 34")
                , 3 to arrayListOf("Section 41", "Section 42", "Section 43", "Section 44")
                , 4 to arrayListOf("Section 51", "Section 52", "Section 53", "Section 54")
        )

        private var currentlyExpanded: Int = -1

        fun generateChapterTitles(): ArrayList<String> {
            val titles = arrayListOf<String>()
            (1..sectionTitles.keys.size).forEach {
                titles.add("Chapter $it")
            }
            return titles
        }
    }

    fun expandData(position: Int) {
        assert(position >= 0 && position < titles.size)

        var positionToExpand = position
        if (currentlyExpanded != -1) { // collapse the previously expanded
            if (currentlyExpanded < position) { // adjust the position to expand
                val sectionCurrentlyExpanded = sectionTitles[currentlyExpanded]
                positionToExpand -= sectionCurrentlyExpanded!!.size
            }
            collapseData(currentlyExpanded)
        }

        // remove each corresponding subsection title
        val sections = sectionTitles[positionToExpand]
        if (positionToExpand < titles.size - 1) {
            titles.addAll(positionToExpand + 1, sections!!.toList())
        } else {
            titles.addAll(sections!!.toList())
        }
        currentlyExpanded = positionToExpand
    }

    fun collapseData(position: Int) {
        assert(position == currentlyExpanded)
        assert(position >= 0 && position < titles.size)

        // remove each corresponding subsection title
        val sections = sectionTitles[position]

        (1..sections!!.size).forEach {
            titles.removeAt(position + 1)
        }
        currentlyExpanded = -1
    }

    fun isExpanded(position: Int): Boolean = position == currentlyExpanded

    override fun toString(): String {
        val buffer = StringBuffer("[")
        titles.forEach {
            buffer.append(it).append(" ")
        }
        buffer.append(" ")
        return buffer.toString()
    }
}