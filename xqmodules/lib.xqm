xquery version "3.0";

module namespace lib = "lib";

import module namespace lib2 = 'lib2';

declare function lib:test($message as xs:string?) {
    element p {'Hello ' || $message || ' from lib'}, 
    lib2:test2($message)
};
