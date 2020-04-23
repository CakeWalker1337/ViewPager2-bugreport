package com.example.test

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * View Pager Adapter. Contains [titles] and [fragments].
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments: MutableList<Fragment> = mutableListOf()
    private val titles: MutableList<String> = mutableListOf()

    /**
     * Add [fragment] and [title]
     */
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getItem(aPosition: Int) = fragments[aPosition]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}