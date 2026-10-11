# PasswordSecurity

A Java utility library for generating secure passwords, validating password security policies, and computing password combinatorial entropy.

## Features

- **Password Generation (`generatePassword`)**:
  - Customizable options for lowercase, uppercase, digits, and special characters.
  - Guarantees at least one character from each selected category.
  - Uses the **Fisher-Yates shuffle algorithm** to eliminate predictable character positioning.

- **Password Policy Enforcement (`enforceStrongPasswordRequirements`)**:
  - Validates minimum length (8+ characters).
  - Checks for the presence of uppercase, lowercase, numeric, and special characters.
  - Provides clear, localized diagnostic feedback on missing criteria.

- **Combinatorial Possibilities Calculation (`calculate_possibilities`)**:
  - Identifies active character pools (lowercase, uppercase, numbers, special characters).
  - Calculates total password search space:
    $$\text{Possibilities} = (\text{Pool Size})^{\text{Length}}$$

## Requirements & Building

- Java Development Kit (JDK 8 or higher)

### Compile
```bash
javac PasswordSecurity.java
```

### Run
```bash
java PasswordSecurity
```

## Contributors & Developers

- **Giannis Papavlas** (`giannispapavla6@gmail.com`)
- **Siaftoulis**
