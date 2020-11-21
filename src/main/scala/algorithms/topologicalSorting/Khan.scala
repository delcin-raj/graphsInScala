package algorithms.topologicalSorting

import algorithms.traversals.DFA.proceduralDFS
import representing.graphs.DirectedGraph

import scala.collection.mutable

object Khan {
  def khanSort[V](graph: DirectedGraph[V]): Option[List[V]] = {
    val inBoundCount = mutable.HashMap[V,Int]()
    var start = true

    def validEntryPoints(removableVertices: List[V]): List[V] =
      removableVertices match {
        case Nil =>
          // initial phase
          if (start) {
            start = false
            graph.vertices.foreach(v => inBoundCount.addOne(v->0) )
            graph.vertices.foreach{
              v => graph.neighbors(v)
                .foreach(u => inBoundCount.update(u , inBoundCount(u)+1))
            }
            inBoundCount.filter{ case (_,n) => n == 0}
              .map{case (u,_) => u}.toList
          }
          else Nil
        case v :: vs =>
          // removing the vertex v
          val neighbors = graph.neighbors(v)
          neighbors.foreach(u => inBoundCount(u) -= 1)
          neighbors.filter(inBoundCount(_) == 0) ++ validEntryPoints(vs)
      }
    var result: List[V] = Nil
    var residual: List[V] = validEntryPoints(Nil)
    if (residual.isEmpty) None
    else {
      while(residual.nonEmpty) {
        result ++= residual
        residual = validEntryPoints(residual)
      }
      Some(result)
    }
  }

  def dfsKhan[V](graph: DirectedGraph[V]): Option[List[V]] = {
    val cycle = false // assumes that there is no cycle for now
    if (cycle) {
      None
    }
    else {
      var stack = List[V]()
      val _ = proceduralDFS(graph, (v: V) => stack = v :: stack) // dfs tree
      Some(stack)
    }
  }
}
