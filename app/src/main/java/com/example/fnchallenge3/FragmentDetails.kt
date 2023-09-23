package com.example.fnchallenge3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FragmentDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Mendapatkan argumen yang dikirimkan dari FragmentMenu
        val args = arguments

        // Menampilkan data makanan di tampilan Detail
        val imgFood: ImageView = view.findViewById(R.id.imgFood)
        val txtFoodName: TextView = view.findViewById(R.id.txtFoodName)
        val txtFoodPrice: TextView = view.findViewById(R.id.txtFoodPrice)
        val txtFoodDescription: TextView = view.findViewById(R.id.txtFoodDescription)
        val txtLocation: TextView = view.findViewById(R.id.txtLocation)
        val txtGoogleMaps: TextView = view.findViewById(R.id.txtGoogleMaps)
        val btnAddToCart: Button = view.findViewById(R.id.btnAddToCart)

        if (args != null) {
            val name = args.getString("name", "")
            val price = args.getString("price", "")
            val description = args.getString("description", "")
            val imageRes = args.getInt("imageRes", 0)
            val restaurantAddress = args.getString("restaurantAddress", "")
            val googleMapsUrl = args.getString("googleMapsUrl", "")

            imgFood.setImageResource(imageRes)
            txtFoodName.text = name
            txtFoodPrice.text = price
            txtFoodDescription.text = description
            txtLocation.text = restaurantAddress

            // Menambahkan onClickListener untuk membuka Google Maps
            txtGoogleMaps.setOnClickListener {
                // Memanggil fungsi openGoogleMaps dari MainActivity
                (activity as MainActivity).openGoogleMaps(googleMapsUrl)
            }

            // Mengubah teks pada tombol "Tambah Ke Keranjang" dengan harga yang sesuai
            btnAddToCart.text = "Tambah Ke Keranjang - $price"
        }

        // Kembali ke tampilan sebelumnya saat tombol "Back" diklik
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}
