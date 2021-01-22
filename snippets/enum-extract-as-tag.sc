# Original Author: Westerbly (radgeRayden) Snaydley
# Date: 2021-01-20

# Helper to more efficiently unwrap an Enum value when already certain of what tag to
# expect.

inline extract-as-tag (ev tag)
    """"Force extraction of enum value as one of its tags.
        Expected arguments:
            ev = Enum
            tag = Symbol
        Will abort if unwrapping a value whose tag doesn't match what was requested.
        Example usage:
        ```
        enum A
            a : i32 i32
        let ev = (A.a)
        let a0 a1 = (extract-as-tag ev 'a)
        ```
    let eT = (typeof ev)
    let ft = (getattr eT tag)
    let lit = ft.Literal

    # safety check
    assert (('literal ev) == lit)
    'unsafe-extract-payload ev ft.Type