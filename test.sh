func1()
{
    local var='func1 local'
    func2
    echo "In func1, var = $var"
}

func2()
{
    echo "In func2, var = $var"
    var="from func2"
	var2="from f2 global?"
}

var=global
func1
echo "global  var = $var"
echo "global  var2 = $var2"
