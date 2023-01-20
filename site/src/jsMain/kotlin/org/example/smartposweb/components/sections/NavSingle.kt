package org.example.smartposweb.components.sections


sealed class NavigationEntry {
    data class NavSingle(val path: String, val title: String, val icon: String? = null) : NavigationEntry()
    data class NavSection(val path:String, val items: List<NavSingle>, val title: String, val icon: String? = null) : NavigationEntry()

}
