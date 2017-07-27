import module namespace lib = 'lib';

declare namespace xslt = 'http://basex.org/modules/xslt';
declare namespace output = 'http://www.w3.org/2010/xslt-xquery-serialization';
declare option output:method 'html';

declare variable $message as xs:string? external := '';

element html {
    element body {
        element h1 {
            xslt:processor() || ' (' || xslt:version() || ')'
        },
        element ul {
            for $n in (1 to 5) return
                element li {$n}
        },
        element p {
            xslt:transform(<test>{$message}</test>, 'views/test.xsl') (: The URL path is relative to application root :)
        }
    }
}


