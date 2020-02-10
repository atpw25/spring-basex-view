import module namespace lib = 'lib';

declare namespace xslt = 'http://basex.org/modules/xslt';
declare namespace output = 'http://www.w3.org/2010/xslt-xquery-serialization';
declare option output:method 'html';

declare variable $message as xs:string? external := 'World';

<html>
    <body>
        <h1>XQuery View</h1>
        <div>
            <p>{'Hello ' || $message || ' from XQuery', for $n in (1 to 3) return $n}</p>
        </div>
        <div>
            <!-- The URL path is relative to application root -->
            <p>{xslt:transform(<test>{$message}</test>, 'views/test.xsl')/result/text()}</p>
        </div>
        <div>{lib:test($message)}</div>
    </body>
</html>

(:~ Using constructor syntax :)
(:
element html {
    element body {
        element h1 {
            'XQuery View'
        },
        element div {
            element p {
                'Hello ' || $message || ' from XQuery',
                for $n in (1 to 3) return $n
            }
        },
        element div {
            xslt:transform(<test>{$message}</test>, 'views/test.xsl')/result/text() (: The URL path is relative to application root :)
        },
        element div {
            lib:test($message)
        }
    }
}
:)

