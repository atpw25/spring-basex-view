import module namespace lib = 'lib';

(:declare namespace output = 'http://www.w3.org/2010/xslt-xquery-serialization';:)
(:declare option output:method 'xml';:)

declare variable $message as xs:string? external := '';

(:map {"result": 'This is from XQuery ' || lib:test($message)}:)

element html {
    element body {
        element h1 {
            lower-case('This is from XQuery ' || $message)
        },
        element ul {
            for $n in (1 to 5) return
                element li {$n}
        }
    }
}


