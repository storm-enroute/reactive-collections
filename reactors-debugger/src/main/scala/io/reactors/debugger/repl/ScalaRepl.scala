package io.reactors
package debugger
package repl



import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter._



class ScalaRepl extends Repl {
  def tpe = "Scala"
  def eval(cmd: String) = Repl.Result(0, "")
}