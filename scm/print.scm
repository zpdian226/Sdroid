(define (intToString i) (String.valueOf i))
(define (printTo x target) (.append target (intToString (eval x))))