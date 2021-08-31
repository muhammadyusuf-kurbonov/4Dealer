package uz.muhammadyusuf.kurbonov.shared.ui.controllers

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

interface ToolbarController {
    fun setActions(newActions: @Composable RowScope.()-> Unit)
    fun clear()
}