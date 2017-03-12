package io.reactors
package remote



import io.reactors.common.Cell
import org.scalatest.FunSuite



class RuntimeMarshalerTest extends FunSuite {
  test("marshal empty non-final class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new NonFinalEmpty, data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[NonFinalEmpty](cell)
    assert(obj.isInstanceOf[NonFinalEmpty])
  }

  test("marshal empty final class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new FinalEmpty, data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[FinalEmpty](cell)
    assert(obj.isInstanceOf[FinalEmpty])
  }

  test("marshal single integer field non-final class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new NonFinalSingleInt(15), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[NonFinalSingleInt](cell)
    assert(obj.x == 15)
  }

  test("marshal single integer field final class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new FinalSingleInt(15), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[FinalSingleInt](cell)
    assert(obj.x == 15)
  }

  test("marshal single long field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleLong(15), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleLong](cell)
    assert(obj.x == 15)
  }

  test("marshal single int field class, when buffer is small") {
    val data = new Data.Linked(16, 16)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new FinalSingleInt(15), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[FinalSingleInt](cell)
    assert(obj.x == 15)
  }

  test("marshal single long field class, when buffer is small") {
    val data = new Data.Linked(16, 16)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleLong(15), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleLong](cell)
    assert(obj.x == 15)
  }

  test("marshal single double field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleDouble(15.0), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleDouble](cell)
    assert(obj.x == 15.0)
  }

  test("marshal single double field class, when buffer is small") {
    val data = new Data.Linked(16, 16)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleDouble(15.0), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleDouble](cell)
    assert(obj.x == 15.0)
  }

  test("marshal single float field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleFloat(15.0f), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleFloat](cell)
    assert(obj.x == 15.0f)
  }

  test("marshal single float field class, when buffer is small") {
    val data = new Data.Linked(16, 16)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleFloat(15.0f), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleFloat](cell)
    assert(obj.x == 15.0f)
  }

  test("marshal single byte field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleByte(7), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleByte](cell)
    assert(obj.x == 7)
  }

  test("marshal single boolean field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleBoolean(true), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleBoolean](cell)
    assert(obj.x == true)
  }

  test("marshal single char field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleChar('a'), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleChar](cell)
    assert(obj.x == 'a')
  }

  test("marshal single short field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new SingleShort(17), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[SingleShort](cell)
    assert(obj.x == 17)
  }

  test("marshal mixed primitive field class") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new MixedPrimitives(17, 9, 2.1, true, 8.11f, 'd'), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[MixedPrimitives](cell)
    assert(obj.x == 17)
    assert(obj.y == 9)
    assert(obj.z == 2.1)
    assert(obj.b == true)
    assert(obj.f == 8.11f)
    assert(obj.c == 'd')
  }

  test("marshal object with a final class object field") {
    val data = new Data.Linked(128, 128)
    val cell = new Cell[Data](data)
    RuntimeMarshaler.marshal(new FinalClassObject(new FinalSingleInt(17)), data)
    println(data.byteString)
    val obj = RuntimeMarshaler.unmarshal[FinalClassObject](cell)
    assert(obj.inner.x == 17)
  }
}


class NonFinalEmpty


final class FinalEmpty


class NonFinalSingleInt(val x: Int)


final class FinalSingleInt(val x: Int)


class SingleLong(val x: Long)


class SingleDouble(val x: Double)


class SingleFloat(val x: Float)


class SingleByte(val x: Byte)


class SingleBoolean(val x: Boolean)


class SingleChar(val x: Char)


class SingleShort(val x: Short)


class MixedPrimitives(
  val x: Int, var y: Short, val z: Double, val b: Boolean, val f: Float, val c: Char
)


class FinalClassObject(val inner: FinalSingleInt)