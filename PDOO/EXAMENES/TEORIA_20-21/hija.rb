require_relative 'padre.rb'

class Hija < Padre
  @@BETA=1.3

  def initialize
    @atributo_c=33
  end
end

Padre.new.salida
Hija.new.salida
