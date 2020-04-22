package dinocpu
import chisel3._
import chisel3.util._

class SimpleAdder extends Module{
  val io = IO(new Bundle{
    val inputx = Input(UInt(32.W))
    val inputy = Input(UInt(32.W))
    val result = Output(UInt(32.W))
  )}
  io.result :=io.inputx+io.inputy

}

class SimpleSystem extends Module{
  val io = IO(new Bundle{
    val success = Output(Bool())
  })
  val SimpleAdder1 = Module(new SimpleAdder())
  val SimpleAdder2 = Module(new SImpleAdder())

  SimpleAdder2.io.inputx := SimpleAdder1.io.result
  
  val Reg1 = RegInit(0.U)
  val Reg2 = RegInit(1.U)
  SimpleAdder1.io.inputx:=Reg1
  SimpleAdder1.io.inputy:=Reg2
  Reg1:=SimpleAdder1.io.result
  Reg2:=SimpleAdder2.io.result
  SimpleAdder2.io.inputy:=3.U(32.W)
  io.success:=mux(SimpleAdder2.io.result===128.U(32.W),true.B,false.B)


}
