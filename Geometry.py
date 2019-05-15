import math

class Vector:

    X = 0

    Y = 0



    def __init__(self, x, y):

        self.X = x

        self.Y = y



    def print(self):

        print(self.X, self.Y)



    def __add__(self, other):

        return Vector(self.X + other.X,

                      self.Y + other.Y)



    def __mul__(self, other):

        return self.X * other.X + \
               self.Y * other.Y


    def get_len(self):

        return math.sqrt(self * self)


    def get_angle(self, other):



        if self.get_len() == 0 or other.get_len() == 0:

            raise Exception("Bad input!")



        s_mul = self * other

        cos = s_mul / (self.get_len()

                       * other.get_len())

        return math.degrees(math.acos(cos))


class Coordinate:

    X = 0

    Y = 0

    def __init__(self, x, y):

        self.X = x

        self.Y = y

    def make_vector(self, other):

        return Vector(other.X - self.X,
                      other.Y - self.Y)

x1, y1 = map(int, input().split())
x2, y2 = map(int, input().split())
x3, y3 = map(int, input().split())


a = Coordinate(x1, y1)
b = Coordinate(x2, y2)
c = Coordinate(x3, y3)


D = a.make_vector(b)
E = a.make_vector(c)
F = b.make_vector(c)
G = c.make_vector(b)


ABC = D.get_angle(G)
ACB = E.get_angle(F)
BAC = 180 - ABC - ACB


if ABC == 90 or ACB == 90 or BAC == 90:
    print('Прямоугольный')
elif ABC > 90 or ACB > 90 or BAC > 90:
    print('Тупоугольный')
else:
    print('Остроугольный')
