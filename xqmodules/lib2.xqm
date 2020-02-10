xquery version "3.0";

module namespace lib2 = "lib2";

declare function lib2:test2($message) {
    element p { 
        attribute style {'color: red'},
        'Hello ' || $message || ' from lib2'
    }
};
