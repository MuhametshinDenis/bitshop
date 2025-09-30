"use client"
import { CircleUser, Heart, Home, LayoutGrid, ShoppingCart } from "lucide-react"
import Link from "next/link"
import { usePathname } from "next/navigation"
import { ROUTES } from "@/constants/routes"

export const navbarItems = [
  { label: "Главная", link: ROUTES.HOME, icon: Home },
  { label: "Категории", link: ROUTES.CATEGORIES, icon: LayoutGrid },
  { label: "Избранное", link: ROUTES.FAVORITES, icon: Heart },
  { label: "Корзина", link: ROUTES.CART, icon: ShoppingCart },
  { label: "Профиль", link: ROUTES.PROFILE, icon: CircleUser },
]

export function Navbar() {
  const pathname = usePathname()
  return (
    <div className="absolute bottom-0 left-0 w-full">
      <div className="grid grid-cols-5">
        {navbarItems.map(({ label, link, icon: Icon }) => (
          <Link
            href={link}
            key={label}
            className={`w-full p-2 items-center text-center ${pathname === link ? "text-primary" : "text-muted-foreground"} transition-all duration-200 hover:bg-muted rounded-full`}
          >
            <Icon className="w-full" size={22} />
            <p className="text-xs mt-1">{label}</p>
          </Link>
        ))}
      </div>
    </div>
  )
}
