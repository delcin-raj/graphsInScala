package playground

import algorithms.topologicalSorting.Khan.{dfsKhan, khanSort}
import representing.graphs.Graph

object Khan extends App{
  val graph = Graph[Int]()
    .addEdge(9,5)
    .addEdge(8,6)
    .addEdge(8,7)
    .addEdge(5,2)
    .addEdge(5,3)
    .addEdge(6,4)
    .addEdge(7,3)
    .addEdge(2,1)
    .addEdge(3,1)
    .addEdge(4,1)
  println(khanSort(graph))
  println(dfsKhan(graph))
}
