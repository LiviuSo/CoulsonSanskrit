package com.example.lvicto.coultersanskrit

import kotlin.collections.ArrayList

/**
 * Returns the titles/headers of the chapters/sections
 */
class TitlesHelper(private var titles: ArrayList<String>) { // todo: write unit tests

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
            titles.addAll(positionToExpand + 1, sections!!.map { MyApplication.application.getString(it) })
        } else {
            titles.addAll(sections!!.map { MyApplication.application.getString(it) })
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

    companion object {
        private var currentlyExpanded: Int = -1

        fun generateChapterTitles(): ArrayList<String> {
            val titles = arrayListOf<String>() // todo find how to access layered it with(arrayListOf()) { (0..2).forEach() { ....}}
            (1..sectionTitles.keys.size).forEach {
                titles.add("${MyApplication.application.getString(R.string.chapter)} $it")
            }
            return titles
        }

        private var sectionTitles = sortedMapOf(
                0 to arrayListOf(R.string.section_01_01,
                        R.string.section_01_02,
                        R.string.section_01_03,
                        R.string.section_01_04,
                        R.string.section_01_05,
                        R.string.section_01_06,
                        R.string.section_01_07,
                        R.string.section_01_08,
                        R.string.section_01_09)
        )
    }

}