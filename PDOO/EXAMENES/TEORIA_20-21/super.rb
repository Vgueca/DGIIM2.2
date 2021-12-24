class Elemento
  def resultado
    "Resultado Elemento"
  end
end

class Padre
  def resultado
    "Resultado Padre"
  end

  def metodo
    Elemento.new
  end
end

class Hija<Padre
  def resultado
    "Resultado Hija"
  end

  def metodo
    super.resultado
  end
end

puts Hija.new.metodo
