package representing.graphs.PlayGround

import representing.graphs.Graph

object TestGraph extends App {
  var graph = Graph[String]()
  graph = graph.addEdge("T","H")
  graph = graph.addEdge("Y","P")
  println(graph.edges)
  println(graph.vertices)
  println(graph.neighbors("t"))
  println(graph.neighbors("T"))
}
