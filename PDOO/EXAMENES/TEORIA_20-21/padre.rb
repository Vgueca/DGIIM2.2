class Padre

  @@BETA=1.1

  def initialize
    @atributo_a=11
    @atributo_b=22
  end

  def salida
    puts @atributo_a
    puts @atributo_b
    puts @@BETA
  end

end

Padre.new.salida
