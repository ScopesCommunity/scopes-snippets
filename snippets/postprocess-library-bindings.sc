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
            let new-name = (rslice name (countof pattern))
            'bind scope (Symbol new-name) v
        else
            # return the unmodified Scope.
            scope

# Original Author: Westerbly (radgeRayden) Snaydley
# Date: 2021-01-21

# LICENSE: Unlicense

# This is free and unencumbered software released into the public domain.

  Anyone is free to copy, modify, publish, use, compile, sell, or
  distribute this software, either in source code form or as a compiled
  binary, for any purpose, commercial or non-commercial, and by any
  means.

  In jurisdictions that recognize copyright laws, the author or authors
  of this software dedicate any and all copyright interest in the
  software to the public domain. We make this dedication for the benefit
  of the public at large and to the detriment of our heirs and
  successors. We intend this dedication to be an overt act of
  relinquishment in perpetuity of all present and future rights to this
  software under copyright law.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
  OTHER DEALINGS IN THE SOFTWARE.

  For more information, please refer to <http://unlicense.org/>
