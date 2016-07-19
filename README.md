BitSetCompat: A BitSet compatibility class
==========================================

Context
-------

New methods have been added to the ```BitSet``` class since Android Kitkat, and the Android API does not help us with native compatibility class.


Description
-----------

BitSet compatibility class for working with both pre-Kitkat and post-Kitkat versions of Android

This is a compatibility class to have the methods equivalents for:
- ```BitSet valueOf (byte[] bytes)```
- ```byte[] toByteArray ()```


Code example
------------

```
// For post-Kitkat
BitSet bitset = BitSet.valueOf(bytes);
bitset.toByteArray();

// For both post and pre-Kitkat
BitSet bitset = BitSetCompat.valueOf(byteArrayToConvert)
byte[] bytes = BitSetCompat.toByteArray(bitSet);
```