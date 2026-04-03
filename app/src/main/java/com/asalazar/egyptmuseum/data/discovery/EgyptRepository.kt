package com.asalazar.egyptmuseum.data.discovery

import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType

interface EgyptRepository {
    fun getCategories(): List<Category>
    fun getCategoryById(id: CategoryType): Category?
}
