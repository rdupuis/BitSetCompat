/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


import android.os.Build;
import java.util.BitSet;

/**
 * Compatibility class for BitSet to have {@code valueOf} and {@code toByteArray} methods
 * for pre-Kitkat versions of Android
 */
public class BitSetCompat {
    /**
     * Convert a {@code byte[]} to {@code BitSet}
     * Working for both pre and post-Kitkat versions of Android
     * @param bytes The byte array to convert in BitSet
     * @return A {@code BitSet} corresponding to the {@code byte[]}, interpreted as a little-endian
     *         sequence of bits.
     */
    public static BitSet valueOf(byte[] bytes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return BitSet.valueOf(bytes);
        }
        else{
            return valueOfCompat(bytes);
        }
    }

    /**
     * Get the {@code byte[]} corresponding to a {@code BitSet}
     * Working for both pre and post-Kitkat versions of Android
     * @param bitset The {@code BitSet} to get the byte array from
     * @return A new {@code byte[]} containing a little-endian representation the bits of
     *         the {@code BitSet} in argument
     */
    public static byte[] toByteArray(BitSet bitset){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitset.toByteArray();
        }
        else{
            return toByteArrayCompat(bitset);
        }
    }

    /// Do the same as {@code BitSet.valueOf}, which is available only from the Android Kitkat
    private static BitSet valueOfCompat(byte[] bytes){
        BitSet bitset = new BitSet();
        for (int i = 0; i < bytes.length * 8; ++i) {
            if ((bytes[i / 8] & (1 << (i % 8))) > 0) {
                bitset.set(i);
            }
        }
        return bitset;
    }

    /// Do the same as {@code BitSet.toByteArray}, which is available only from the Android Kitkat
    private static byte[] toByteArrayCompat(BitSet bitset) {
        byte[] bytes = new byte[(bitset.length() + 7) / 8];

        for (int i=0; i<bitset.length(); ++i) {
            if (bitset.get(i)) {
                bytes[i/8] |= 1<<(i%8);
            }
        }

        return bytes;
    }
}
