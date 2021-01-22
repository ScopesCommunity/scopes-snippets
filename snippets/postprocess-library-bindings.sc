# Original Author: Westerbly (radgeRayden) Snaydley
# Date: 2021-01-21

# Very often C APIs use prefixes for namespacing. This function goes through all symbols in a Scope that
# match a certain pattern and append a version of the binding without a prefix.

# Example of a binding that would work with this:
    vvv bind sdl
    do
        let module = (include "SDL2/SDL.h")
        using module.extern
        using module.const
        using module.define
        using module.typedef
        unlet module
        locals;

    let sdl = (sanitize-bindings sdl "^SDL_")

inline sanitize-bindings (scope pattern)
    # Scope objects are immutable, so we have to build the new one iteratively.
    fold (scope = scope) for k v in scope
        # the scope generator returns symbols as boxed Values, so we need to unbox to Symbol first.
        let name = (k as Symbol as string)
        # the 'match? method in string uses the C++ regex engine.
        let match? start end = ('match? pattern name)
        if match?
            # here we make the assumption that we are always removing a prefix, so rslice suffices.
            let new-name = (rslice name end)
            'bind scope (Symbol new-name) v
        else
            # return the unmodified Scope.
            scope