package com.zzp.dtrip.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zzp.dtrip.fragment.TabFragment

class SocietyAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return TabFragment.newInstance(position)
    }
}
