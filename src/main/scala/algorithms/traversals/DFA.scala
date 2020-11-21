package algorithms.traversals

import representing.graphs.Graph

import scala.collection.mutable
// When executing the algorithm we are maintaining state.
// How does is this works if we are to parallelize dfa?
// Parallel executing of dfa in scala?


object DFA {
  def proceduralDFS[V](graph: Graph[V],f: V => Unit): Map[V,Option[V]] = {
    val visited = mutable.HashSet[V]()
    val parentOf = mutable.HashMap[V,Option[V]]()
    def recurse(node: V): Unit = {
      visited.add(node)
      for (x <- graph.neighbors(node)) {
        // HashSet.add(elem) will return true if elem is newly added else false
        if (visited.add(x)) {
          parentOf.addOne(x -> Some(node)) // mark the parent
          recurse(node) // explore
        }
      }
      // after the node was completed
      // postApply for khan
      f(node)
    }
    for (x <- graph.vertices) {
      parentOf(x) = None
      if (visited.add(x)) recurse(x)
    }
    parentOf.toMap
  }

  def functionalDFS[V](start: V,graph: Graph[V],f: V => Unit,
                       visited: Set[V]): Set[V] = {
    if (visited(start)) visited
    else graph.neighbors(start).foldLeft(visited + start) {(visitedSoFar,node) =>
      functionalDFS(node,graph,f,visitedSoFar) ++ visitedSoFar
    }
  }

  def nonRecDFS[V](start: V,graph: Graph[V],f: V => Unit): Unit = {
    var stack = List[V](start)
    val visited = mutable.HashSet[V](start)
    while (stack.nonEmpty) {
      val currNode = stack.head
      f(currNode)
      val newlyVisited = graph.neighbors(currNode).filterNot(visited.contains(_))
      stack = newlyVisited ++ stack.tail
      visited.addAll(newlyVisited)
    }
  }

  def BFS[V](start: V,graph: Graph[V],f: V => Unit) = {
    var queue = mutable.Queue[V](start)
    val visited = mutable.HashSet[V](start)

    while(queue.nonEmpty) {
      val currNode = queue.dequeue()
      f(currNode)
      val newlyVisited = graph.neighbors(currNode).filterNot(visited.contains(_))
      queue ++= newlyVisited
      visited.addAll(newlyVisited)
    }
  }
}
