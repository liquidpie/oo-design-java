# encryption

### 1. XOR Encryption Principle

If an integer a and any integer b are exclusive or twice, the result is the integer a itself, that is: a == a ^ b ^ b.

Here a is the original data to be encrypted, and b is the key. A ^ b is the encryption process, and the result of exclusive or is the encrypted ciphertext; a ^ b and key b exclusive or are the decryption process, and the result is the original data a itself.

    a = Original data
    b = secret key

    // One XOR, encrypted to ciphertext
    c = a ^ b

    // Second exclusive or, decrypt to get the original data (d == a)
    d = c ^ b

XOR encryption if the original and ciphertext are known at the same time, the secret key can be calculated by comparing the original and the password. Therefore, XOR encryption has low security and is generally only used for simple encryption.
