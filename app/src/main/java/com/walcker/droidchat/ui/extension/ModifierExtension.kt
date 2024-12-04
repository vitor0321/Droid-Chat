package com.walcker.droidchat.ui.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp

internal fun Modifier.droidChatBottomBorder(
    color: Color,
    strokeWidth: Dp,
) = this.drawBehind {
    val strokeWithPx = strokeWidth.toPx()

    val width = size.width
    val height = size.height - strokeWithPx / 2

    drawLine(
        color = color,
        start = Offset(0f, height),
        end = Offset(width, height),
        strokeWidth = strokeWithPx
    )
}

private class BottomBorderNode(
    var color: Color,
    var strokeWidth: Dp,
) : DrawModifierNode, Modifier.Node() {

    override fun ContentDrawScope.draw() {
        val strokeWithPx = strokeWidth.toPx()

        val width = size.width
        val height = size.height - strokeWithPx / 2

        drawLine(
            color = color,
            start = Offset(0f, height),
            end = Offset(width, height),
            strokeWidth = strokeWithPx
        )
    }
}

private data class BottomBorderElement(
    val color: Color,
    val strokeWidth: Dp,
) : ModifierNodeElement<BottomBorderNode>() {
    override fun create(): BottomBorderNode {
        return BottomBorderNode(color, strokeWidth)
    }

    override fun update(node: BottomBorderNode) {
        node.color = color
        node.strokeWidth = strokeWidth
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "Bottom Border"
        properties["color"] = color
        properties["strokeWidth"] = strokeWidth
    }
}

fun Modifier.droidChatBottomBorderCustom(color: Color, strokeWidth: Dp): Modifier =
    this then BottomBorderElement(color, strokeWidth)