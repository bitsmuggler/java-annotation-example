package com.bitsmuggler.learning;


@SpecialTesterInfo(priority = SpecialTesterInfo.Priority.HIGH, createdBy = "bitsmuggler", tags = {"test1", "test2"})
public class SpecialTestExample {

  @SpecialTest
  void testA() {
    if (true) {
      throw new RuntimeException("This test always failed");
    }
  }

  @SpecialTest(enabled = false)
  void testB() {
    if (false)
      throw new RuntimeException("This test always passed");
  }

  @SpecialTest
  void testC() {
    if (10 > 1) {
      // do nothing, this test always passed.
    }
  }
}
