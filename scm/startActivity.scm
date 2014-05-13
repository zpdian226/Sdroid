(define context LoadResource.sContext$)

(define tv (.findViewById context org.zpdian.sdroid.R$id.t1$))
(.setText tv "hello scheme")

(define i (Intent. context TestActivity.class))
(.startActivity context i)
(printTo "Another Activity Started" target)
