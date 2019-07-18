from PIL import Image
import io

image_data = []

with open("digimon.jpg", "rb") as image:
  f = image.read()
  print(len(f))
  b = bytearray()
  b.append(3)
  print(len(b))
  image_data = b
image = Image.open(io.BytesIO(image_data))
image.show()

"""
import PIL
from PIL import Image
from PIL import ImageDraw
from PIL import ImageFont
import urllib.request

with urllib.request.urlopen('http://pastebin.ca/raw/2311595') as in_file:
    hex_data = in_file.read()
print(hex_data)
img = Image.frombuffer('RGB', (320,240), hex_data) #i have tried fromstring
draw = ImageDraw.Draw(img)
font = ImageFont.truetype("arial.ttf",14)
draw.text((0, 220),"This is a test11",(255,255,0),font=font)
draw = ImageDraw.Draw(img)
img.save("a_test.jpg")
"""