package com.jcry87.kotlinstores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.jcry87.kotlinstores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener, MainAux {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        /*mBinding.btnSave.setOnClickListener{
            val store = StoreEntity(name = mBinding.etName.text.toString().trim())

            Thread{
                StoreApplication.database.storeDao().addStore(store)
            }.start()

            mAdapter.add(store)
        }*/

        mBinding.fabAddStore.setOnClickListener{
            launchEditFragment()
        }
        setupRecyclerView()
    }

    private fun launchEditFragment(args : Bundle? = null) {
        val fragment = EditStoreFragment()
        if(args != null) fragment.arguments = args
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.main_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        hideFab()
    }

    private fun setupRecyclerView() {
        mAdapter = StoreAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this,2)
        getStores()

        mBinding.rvStore.apply{
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun getStores(){
        Thread {
            val stores = StoreApplication.database.storeDao().getAllStores()
            mAdapter.setStores(stores)
        }.start()
    }

    override fun onClick(storeId: Long) {
        val args = Bundle()
        args.putLong(getString(R.string.arg_id), storeId)

        launchEditFragment(args)
    }

    override fun onFavoriteStore(storeEntity: StoreEntity) {
        storeEntity.isFavorite = !storeEntity.isFavorite
        Thread {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            mAdapter.update(storeEntity)
        }.start()
    }

    override fun onDeleteStore(storeEntity: StoreEntity) {
        Thread{
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            mAdapter.delete(storeEntity)
        }.start()
    }

    // Main Aux
    override fun hideFab(isVisible: Boolean) {
        if(isVisible) mBinding.fabAddStore.show() else mBinding.fabAddStore.hide()
    }

    override fun addStore(storeEntity: StoreEntity) {
        mAdapter.add(storeEntity)
    }

    override fun updateStore(storeEntity: StoreEntity) {

    }
}