package com.kilicma.foodappmvvm.UI.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kilicma.foodappmvvm.Model.MenuModel
import com.kilicma.foodappmvvm.R
import com.kilicma.foodappmvvm.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), MenuAdapter.MenuItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        viewModel.getMenuList()
        initObservers()

        return binding.root
    }

    private fun initObservers(){
        viewModel.menuList.observe(viewLifecycleOwner){list->
            initRecycler(list)
        }
    }
    private fun initRecycler(menuList: List<MenuModel>){
        val menuAdapter = MenuAdapter(menuList,this)
        val _layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewMenus.apply {
            layoutManager = _layoutManager
            adapter = menuAdapter
        }
    }

    override fun onItemClicked(menuModel: MenuModel.MenuItems) {
        Toast.makeText(context,"${menuModel.menuName} sepet eklendi !", Toast.LENGTH_LONG).show()
    }

    override fun onItemClickedNavigate(menuModel: MenuModel.MenuItems) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(menuModel))
    }
}