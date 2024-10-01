package com.zzp.dtrip.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zzp.dtrip.R
import com.zzp.dtrip.adapter.SocietyAdapter
import com.zzp.dtrip.view.CustomDialog

class SocietyFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_society, container, false)

            val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
            val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
            val addButton: FloatingActionButton = view.findViewById(R.id.addButton)
            tabLayout.setTabTextColors(
                ContextCompat.getColor(requireContext(), R.color.normal_color),
                ContextCompat.getColor(requireContext(), R.color.selected_color)
            )
            addButton.setOnClickListener {
                val alertDialog = CustomDialog(requireContext())
                alertDialog.show("因相关政策及备案需求，论坛功能关闭中")
            }
            viewPager.adapter = SocietyAdapter(requireActivity())

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> tab.text = "全部"
                    1 -> tab.text = "闲聊"
                    2 -> tab.text = "交友"
                    3 -> tab.text = "互助"
                }

            }.attach()

            return view
        }
}