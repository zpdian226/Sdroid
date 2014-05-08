;; from http://blog.chinaunix.net/uid-13539494-id-3289878.html
(define (merge left right)
  (cond ((null? left)
         right)
        ((null? right)
         left)
        (else
         (let f ((left left) (right right))
           (cond ((null? left)
                  right)
                 ((null? right)
                  left)
                 ((<= (car left) (car right))
                  (cons (car left) (f (cdr left) right)))
                 ((> (car left) (car right))
                  (f (cons (car right) left) (cdr right))))))))


(merge '(1 7 8 ) '(3))

(merge '(0 7 11 44 ) '())

(define (mergesort ls)
  (cond
    ((null? ls) '())
    ((pair? ls)
     (let ((left (mergesort (car ls)))
           (right (mergesort (cdr ls))))
         (merge left right)))
    (else (list ls))))

;;(display (mergesort '(9 2 4 10 1 0 2 38)))
(printTo "load: /sdcard/mergesort.scm\n" target)
(printTo "before merge sort:\n" target)
(printTo '(list 9 2 4 10 -1 0 2 38) target)
(printTo "\nafter merge sort:\n" target)
(printTo '(mergesort (list 9 2 4 10 -1 0 2 38)) target)