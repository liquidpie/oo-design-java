# encryption

### 1. XOR Cipher

If an integer a and any integer b are exclusive or twice, the result is the integer a itself, that is: a == a ^ b ^ b.

Here a is the original data to be encrypted, and b is the key. A ^ b is the encryption process, and the result of exclusive or is the encrypted ciphertext; a ^ b and key b exclusive or are the decryption process, and the result is the original data a itself.

    a = Original data
    b = secret key

    // One XOR, encrypted to ciphertext
    c = a ^ b

    // Second exclusive or, decrypt to get the original data (d == a)
    d = c ^ b

XOR encryption if the original and ciphertext are known at the same time, the secret key can be calculated by comparing the original and the password. Therefore, XOR encryption has low security and is generally only used for simple encryption.

### 2. Caesar Cipher

The Caesar Cipher technique is one of the earliest and simplest method of encryption technique. It’s simply a type of substitution cipher, i.e., each letter of a given text is replaced by a letter some fixed number of positions down the alphabet. For example with a shift of 1, A would be replaced by B, B would become C, and so on.

**Examples**

```text
Text : ABCDEFGHIJKLMNOPQRSTUVWXYZ
Shift: 23
Cipher: XYZABCDEFGHIJKLMNOPQRSTUVW

Text : ATTACKATONCE
Shift: 4
Cipher: EXXEGOEXSRGI
```



