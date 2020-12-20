package playground.advent2020.day20

import playground.utilities.Logger

class ImageCompositor(val l: Logger) {
    fun multiplyCornerTiles(input: Sequence<Sequence<String>>): Long {
        // Parse the input to construct a set of tiles
        val tiles = TileParser(l).parseTiles(input)

        // Find matching tiles for the sides of the tiles
        matchNeighbours(tiles)

        l.debug("Result after matching:\n${tiles.values.joinToString("\n")}")

        // Find the ones with only two neighbours
        val cornerTiles = tiles.values.filter { tile -> tile.adjacents.size == 2 }
        if (cornerTiles.size != 4) error("Found ${cornerTiles.size} corner tiles: $cornerTiles")

        // Multiply their ids together and return the result
        return cornerTiles.map { tile -> tile.id.toLong() }.reduce(Long::times)
    }

    private fun matchNeighbours(tiles: Map<Int, Tile>) {
        // Create a map to map sides to tiles (both reversed and not because tiles can be flipped)
        val sideMap = mutableMapOf<String, MutableSet<Int>>()
        tiles.values.forEach { tile ->
            tile.sides.forEach { side ->
                sideMap.computeIfAbsent(side) { mutableSetOf() }.add(tile.id)
                sideMap.computeIfAbsent(side.reversed()) { mutableSetOf() }.add(tile.id)
            }
        }

        // Now fill the list of adjacent tiles for each tile
        tiles.values.forEach { tile ->
            tile.sides.forEach { side ->
                // If the side matches any id different from the tile itself, then that is an adjacent.
                sideMap[side]?.firstOrNull { id -> id != tile.id }?.let { adjacentId ->
                    tile.adjacents.add(adjacentId)
                }
            }
        }
    }
}

private class TileParser(val l: Logger) {
    fun parseTiles(input: Sequence<Sequence<String>>): Map<Int, Tile> {
        val tiles = mutableMapOf<Int, Tile>()
        input.forEach { blockLines ->
            val block = blockLines.toList()

            // Take the tile id from the first line of the block
            val id = block.first().drop(5).dropLast(1).toInt()

            val topSide = block[1]
            val rightSide = block.drop(1).map { line -> line.last() }.joinToString("")
            val bottomSide = block.last().reversed()
            val leftSide = block.drop(1).map { line -> line.first() }.joinToString("").reversed()

            val tile = Tile(id, topSide, rightSide, bottomSide, leftSide)
            l.debug("Parsed tile: $tile")

            tiles[id] = tile
        }

        return tiles
    }
}

private class Tile(val id: Int, topSide: String, rightSide: String, bottomSide: String, leftSide: String) {
    val sides = listOf(topSide, rightSide, bottomSide, leftSide)
    val adjacents = mutableSetOf<Int>()

    override fun toString(): String {
        return "Tile $id: $sides $adjacents"
    }
}

