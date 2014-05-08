(load "/sdcard/import.scm")
(load "/sdcard/print.scm")
;;(define target (.findViewById LoadResource.sContext$ org.sdroid.R$id.dis$))

(printTo "load: /sdcard/calc.scm\n" target)
(printTo "eval exp: (+ 10 2)\n" target)
(printTo (+ 10 2) target)