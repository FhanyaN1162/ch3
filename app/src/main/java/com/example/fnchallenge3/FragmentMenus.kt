package com.example.fnchallenge3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMenu : Fragment() {
    private var fragmentMenuListener: FragmentMenuListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Inisialisasi RecyclerView
        val recyclerViewMenuGrid: RecyclerView = view.findViewById(R.id.recyclerViewMenuGrid)

        // Inisialisasi data menu makanan Anda
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem(
            "Nasi Goreng",
            "Rp 20.000",
            "Nasi goreng spesial dengan telur dan ayam",
            R.drawable.nasi_goreng,
            "Alamat Restoran 1",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))
        menuItems.add(MenuItem(
            "Mie Goreng",
            "Rp 18.000",
            "Mie goreng lezat dengan udang dan sayuran",
            R.drawable.mie_goreng,
            "Alamat Restoran 2",
            "https://maps.app.goo.gl/2yxKvmefahrrB9u19"
        ))
        menuItems.add(MenuItem(
            "Ayam Goreng",
            "Rp 25.000",
            "Ayam goreng yang gurih dan nikmat",
            R.drawable.ayam_goreng,
            "Alamat Restoran 3",
            "https://maps.app.goo.gl/eQdfDWJbbu2GFCMm9"
        ))
        menuItems.add(MenuItem(
            "Sate Ayam",
            "Rp 15.000",
            "Sate ayam dengan bumbu kacang yang khas",
            R.drawable.sate_ayam,
            "Alamat Restoran 4",
            "https://maps.app.goo.gl/5pN2U8kR9y3QDjBE6"
        ))
        menuItems.add(MenuItem(
            "Burger Cheese",
            "Rp 35.000",
            "Burger dengan keju leleh yang lezat",
            R.drawable.burger_cheese,
            "Alamat Restoran 5",
            "https://maps.app.goo.gl/vNGTFJRV1FFeVko1A"
        ))
        menuItems.add(MenuItem(
            "Sushi Roll (8 potong)",
            "Rp 40.000",
            "Sushi roll dengan berbagai pilihan rasa",
            R.drawable.sushi_roll,
            "Alamat Restoran 6",
            "https://maps.app.goo.gl/cMSAqxLjtVUTNP2Z9"
        ))
        menuItems.add(MenuItem(
            "Kopi Hitam",
            "Rp 10.000",
            "Kopi hitam segar tanpa gula",
            R.drawable.kopi_hitam,
            "Alamat Restoran 7",
            "https://maps.app.goo.gl/8VeZmmZgeRdm68xy5"
        ))
        menuItems.add(MenuItem(
            "Jus Jeruk Segar",
            "Rp 15.000",
            "Jus jeruk segar tanpa tambahan gula",
            R.drawable.jus_jeruk_segar,
            "Alamat Restoran 8",
            "https://maps.app.goo.gl/okiH6dwwbig3YZmJ6"
        ))
        menuItems.add(MenuItem(
            "Air Mineral",
            "Rp 3.000",
            "Air mineral dalam kemasan botol",
            R.drawable.air_mineral,
            "Alamat Restoran 9",
            "https://maps.app.goo.gl/Cx7sxvzhxWFid449A"
        ))
        menuItems.add(MenuItem(
            "Teh Manis Dingin",
            "Rp 5.000",
            "Teh manis dingin dengan es batu",
            R.drawable.teh_manis_dingin,
            "Alamat Restoran 10",
            "https://maps.app.goo.gl/zbizStdmKQD1w9z67"
        ))

        // Buat adapter untuk RecyclerView
        val adapter = MenuAdapter(menuItems) { menuItem ->
            // Ketika item di RecyclerView diklik, buat Bundle untuk mengirim data ke FragmentDetail
            val args = Bundle()
            args.putString("name", menuItem.name)
            args.putString("price", menuItem.price)
            args.putString("description", menuItem.description)
            args.putInt("imageRes", menuItem.imageRes)
            args.putString("restaurantAddress", menuItem.restaurantAddress)
            args.putString("googleMapsUrl", menuItem.googleMapsUrl)

            // Mengirim permintaan untuk menampilkan FragmentDetail
            fragmentMenuListener?.onMenuItemClicked(args)
        }

        // Atur layout manager untuk RecyclerView
        val layoutManager = GridLayoutManager(requireContext(), 2) // 2 kolom
        recyclerViewMenuGrid.layoutManager = layoutManager

        // Set adapter ke RecyclerView
        recyclerViewMenuGrid.adapter = adapter

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMenuListener) {
            fragmentMenuListener = context
        } else {
            throw RuntimeException("$context must implement FragmentMenuListener")
        }
    }

    interface FragmentMenuListener {
        fun onMenuItemClicked(args: Bundle)
    }
}

