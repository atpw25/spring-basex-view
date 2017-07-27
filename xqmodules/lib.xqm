xquery version "3.0";

module namespace lib = "lib";

import module namespace lib2 = 'lib2';

declare function lib:test($m as xs:string?) as xs:string {
    'Hello lib ' || lib2:test2($m)
};
