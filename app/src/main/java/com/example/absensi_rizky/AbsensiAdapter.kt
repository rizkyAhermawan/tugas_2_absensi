package com.example.absensi_rizky

class AbsensiAdapter(
    private val absensiList: List<Absensi>,
    private val onItemClick: (Absensi) -> Unit,
    private val onDeleteClick: (Absensi) -> Unit
) : RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsensiViewHolder {
        val binding = ItemAbsensiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbsensiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbsensiViewHolder, position: Int) {
        val absensi = absensiList[position]
        holder.bind(absensi)
    }

    override fun getItemCount(): Int = absensiList.size

    inner class AbsensiViewHolder(private val binding: ItemAbsensiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(absensi: Absensi) {
            binding.nama.text = absensi.nama
            binding.status.text = absensi.status

            // Mengklik untuk mengedit
            binding.root.setOnClickListener { onItemClick(absensi) }

            // Menambahkan tombol delete
            binding.deleteButton.setOnClickListener { onDeleteClick(absensi) }
        }
    }
}
